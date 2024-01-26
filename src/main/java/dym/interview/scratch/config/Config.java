package dym.interview.scratch.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author dym
 * Date: 24.01.2024
 */
public record Config(
        int columns,
        int rows,
        Map<String, Symbol> symbols,
        Probabilities probabilities,
        @JsonProperty("win_combinations")
        Map<String, WinCombination> winCombinations
) {

    /**
     * Basic validation of config.
     * @return true if config is valid
     */
    boolean isValid() {
        return columns > 0 && rows > 0
                && !symbols.isEmpty()
                && probabilities != null
                && !probabilities.standardSymbols.isEmpty()
                && probabilities.standardSymbols.size() == columns * rows   //and we hope they cover all cells
                && !winCombinations.isEmpty();
    }

    public record Probabilities(
            @JsonProperty("standard_symbols")
            List<CellDistribution> standardSymbols,
            @JsonProperty("bonus_symbols")
            CellDistribution bonusSymbols
    ){}
}
