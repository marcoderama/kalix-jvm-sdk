package com.example.domain;

import com.akkaserverless.javasdk.valueentity.ValueEntityContext;
import com.example.CounterApi;
import com.google.protobuf.Empty;

/** A value entity. */
public class Counter extends AbstractCounter {
    @SuppressWarnings("unused")
    private final String entityId;

    public Counter(ValueEntityContext context) {
        this.entityId = context.entityId();
    }

    @Override
    public CounterDomain.CounterState emptyState() {
        throw new UnsupportedOperationException("Not implemented yet, replace with your empty entity state");
    }

    @Override
    public Effect<Empty> increase(CounterDomain.CounterState currentState, CounterApi.IncreaseValue command) {
        return effects().error("The command handler for `Increase` is not implemented, yet");
    }

    @Override
    public Effect<Empty> decrease(CounterDomain.CounterState currentState, CounterApi.DecreaseValue command) {
        return effects().error("The command handler for `Decrease` is not implemented, yet");
    }

    @Override
    public Effect<Empty> reset(CounterDomain.CounterState currentState, CounterApi.ResetValue command) {
        return effects().error("The command handler for `Reset` is not implemented, yet");
    }

    @Override
    public Effect<CounterApi.CurrentCounter> getCurrentCounter(CounterDomain.CounterState currentState, CounterApi.GetCounter command) {
        return effects().error("The command handler for `GetCurrentCounter` is not implemented, yet");
    }
}
