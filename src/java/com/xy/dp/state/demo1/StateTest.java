package com.xy.dp.state.demo1;

import java.util.Scanner;

public class StateTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatContext chatContext = new ChatContext();

        for (;;) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String output = chatContext.handle(input);
            System.out.println(output.isEmpty()? "(no reply)" : "> " + output);
            if ("bye".equalsIgnoreCase(input))
                break;
        }
    }
}
