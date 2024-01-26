package dym.interview.scratch.components.matchers;

import dym.interview.scratch.config.CombinationGroup;

import java.math.BigDecimal;

/**
 * @author dym
 * Date: 25.01.2024
 */
public record MatchingResult(
        String symbol,
        String combination,
        CombinationGroup group,
        BigDecimal rewardMultiplier
) {
}
