package com.hbvk.jep485;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Gatherer;

class DistinctByGatherer {
    /// Example of a *gatherer*. Stream gatherers, new in Java 24, are an extension point for intermediate
    /// operations on streams.
    ///
    static <T, A> Gatherer<T, ?, T> distinctBy(Function<? super T, ? extends A> classifier) {
        final Gatherer.Integrator<HashMap<A, List<T>>, T, T> integrator = (state, element, downstream) -> {
            A apply = classifier.apply(element);
            state.computeIfAbsent(apply, _ -> new ArrayList<>()).add(element);
            if (state.get(apply).size() == 1) {
                downstream.push(element);
            }
            return true;
        };
        return Gatherer.ofSequential(HashMap::new, integrator);
    }
}
