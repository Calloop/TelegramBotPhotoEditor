package commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AppBotCommand {
    String name();

    String description();

    boolean showInHelp() default false;

    boolean showInKeyboard() default false;
}