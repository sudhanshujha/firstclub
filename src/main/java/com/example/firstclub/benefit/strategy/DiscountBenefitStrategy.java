package com.example.firstclub.benefit.strategy;

import org.springframework.stereotype.Component;
import com.example.firstclub.benefit.BenefitContext;
import com.example.firstclub.benefit.BenefitResult;
import com.example.firstclub.entity.Benefit;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class DiscountBenefitStrategy
        implements BenefitStrategy {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(Benefit benefit) {

        return "DISCOUNT".equalsIgnoreCase(
                benefit.getType()
        );
    }

    @Override
    public BenefitResult apply(
            Benefit benefit,
            BenefitContext context
    ) {

        try {

            JsonNode json =
                    objectMapper.readTree(
                            benefit.getConfigJson()
                    );

            int percentage =
                    json.get("discountPercentage")
                            .asInt();

            long discount =
                    (context.getCartValue() * percentage)
                            / 100;

            return BenefitResult.builder()
                    .applied(true)
                    .message(
                            percentage + "% discount applied"
                    )
                    .discountAmount(discount)
                    .build();

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Invalid discount config: " + ex.getMessage(),
                    ex
            );
        }
    }
}