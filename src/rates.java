import java.util.Map;

public record rates(
        String base_code,
        Map<String, Double> conversion_rates
) {}
