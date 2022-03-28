package com.github.jinahya.assertj.more.java.util.regex;

import com.github.jinahya.assertj.more.hidden.ForAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectArrayAssert;

import java.util.Objects;
import java.util.function.IntConsumer;
import java.util.regex.Pattern;

public interface MorePatternAssert<S extends MorePatternAssert<S>>
        extends MoreJavaUtilRegexAssert<S, Pattern> {

    default S hasFlagsSatisfying(final IntConsumer consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        return ForAssert.applyActual2(
                isNotNull(),
                s -> a -> {
                    consumer.accept(a.flags());
                    return s;
                }
        );
    }

    default S hasFlags(final int expected) {
        return hasFlagsSatisfying(r -> Assertions.assertThat(r).isEqualTo(expected));
    }

    default AbstractMoreMatcherAssert<?> extractingMatcher(final CharSequence input) {
        return ForAssert.applyActual2(
                isNotNull(),
                s -> a -> MoreJavaUtilRegexAssertions.assertMore(a.matcher(input))
        );
    }

    default S hasPattern(final String expected) {
        return ForAssert.applyActual2(
                isNotNull(),
                s -> a -> {
                    Assertions.assertThat(a.pattern()).isEqualTo(expected);
                    return s;
                }
        );
    }

    // ------------------------------------------------------------------------------------- split(CharSequence)String[]

    default ObjectArrayAssert<String> extractingSplit(final CharSequence input) {
        return ForAssert.applyActual2(
                isNotNull(),
                s -> a -> Assertions.assertThat(a.split(input))
        );
    }

    default S splits(final CharSequence input, final String[] expected) {
        extractingSplit(input)
                .isEqualTo(expected);
        return (S) this;
    }

    // ----------------------------------------------------------------------------------- split(CharSequence,I)String[]

    default ObjectArrayAssert<String> extractingSplit(final CharSequence input, final int limit) {
        return ForAssert.applyActual2(
                isNotNull(),
                s -> a -> Assertions.assertThat(a.split(input, limit))
        );
    }

    default S splits(final CharSequence input, final int limit, final String[] expected) {
        extractingSplit(input, limit)
                .isEqualTo(expected);
        return (S) this;
    }

    // ------------------------------------------------------------------------------- splitAsStream(CharSequence)Stream
    default ListAssert<String> extractingSplitAsStream(final CharSequence input) {
        return ForAssert.applyActual2(
                isNotNull(),
                s -> a -> Assertions.assertThat(a.splitAsStream(input))
        );
    }
}
