package dym.interview.scratch;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dym.interview.scratch.components.GameBoard;
import dym.interview.scratch.components.Scratch;
import dym.interview.scratch.config.Config;
import dym.interview.scratch.helpers.Params;

import java.io.File;
import java.io.IOException;

/**
 * @author dym
 * Date: 24.01.2024
 */
public class App {
    public static void main(String[] args) throws IOException {
        Params params = new Params();
        if (params.init(args)) {
            ObjectMapper objectMapper = JsonMapper.builder()
                    .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                    .build();
            Config config = objectMapper.readValue(new File(params.getConfigPath()), Config.class);

            GameBoard output = new Scratch(config).scratch(params.getBettingAmount());

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(System.out, output);
        }
    }
}
