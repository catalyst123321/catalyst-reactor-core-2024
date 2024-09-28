package org.springframework.catalyst.reactor.engine;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class EventFactory<T> {

    ThreadPoolExecutor threadPoolExecutor;


    private EventFactory() {
    }

    public EventFactory(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public CompletableFuture submitMonoProcess(Mono<T> process) {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            return process.blockOptional();
        }, this.threadPoolExecutor);
        return completableFuture;
    }






}
