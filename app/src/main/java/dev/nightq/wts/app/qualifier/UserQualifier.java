package dev.nightq.wts.app.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Nightq on 16/8/10.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface UserQualifier {

}
