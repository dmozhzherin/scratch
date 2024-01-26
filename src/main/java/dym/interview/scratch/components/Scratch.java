package dym.interview.scratch.components;

import dym.interview.scratch.config.CellDistribution;
import dym.interview.scratch.config.Config;

import java.math.BigDecimal;
import java.util.Random;

import static java.math.BigDecimal.ZERO;

/**
 * This class is responsible for distributing symbols across the board.
 * Reward calculation is delegated to {@link GameBoardAnalyzer}.
 * The implementation is thread-safe, i.e. multiple games can be played in parallel with the same config.
 */
public class Scratch {
    private final Config config;

    public Scratch(Config config) {
        this.config = config;
    }

    public GameBoard scratch(BigDecimal bet) {
        return new GameBoardAnalyzer(config).analyze(distribute(), bet);
    }

    private GameBoard distribute() {
        boolean isBonus = config.probabilities().bonusSymbols() != null;
        String[][] board = new String[config.rows()][config.columns()];
        String bonusSymbol = null;
        for (CellDistribution cellDistribution : config.probabilities().standardSymbols()) {
            if (isBonus && new Random().nextBoolean()) {    //once or never (but once is almost guaranteed)
                bonusSymbol = new Distribution(config.probabilities().bonusSymbols()).next();   //save one look across the board
                board[cellDistribution.row()][cellDistribution.column()] = bonusSymbol;
                isBonus = false;
            } else {
                board[cellDistribution.row()][cellDistribution.column()] = new Distribution(cellDistribution).next();
            }
        }
        return new GameBoard(board, ZERO, null, bonusSymbol);
    }

}
