package gr.qa.helperClasses.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Custom annotation for setting the environment. Possible values:
 * - staging
 * - production
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Environment {

    String[] runIn() default "production";

}
