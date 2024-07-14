package test;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: Created by com.zj
 * @Date: 2021/09/21 下午 11:09
 */
public class Main {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "stage0")
                .thenApply(s -> s + "-stage1")
                .thenApplyAsync(s -> {
                    // insert async call here...
                    return s + "-stage2";
                })
                .thenApply(s -> s + "-stage3")
                .thenApplyAsync(s -> {
                    // insert async call here... sss
                    return s + "-stage4";
                })
                .thenAccept(System.out::println);



    }
}
