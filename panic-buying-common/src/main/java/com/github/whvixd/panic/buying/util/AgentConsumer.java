package com.github.whvixd.panic.buying.util;

import java.util.Objects;

/**
 * Created by wangzhx on 2019/7/22.
 */
@FunctionalInterface
public interface AgentConsumer {
    void accept();

    default AgentConsumer andThen(AgentConsumer agentConsumer) {
        Objects.requireNonNull(agentConsumer);
        return ()->{accept(); agentConsumer.accept();};
    }
}
