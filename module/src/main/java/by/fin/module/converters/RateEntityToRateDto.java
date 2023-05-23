package by.fin.module.converters;


import by.fin.module.core.dto.Rate;
import by.fin.module.entity.RateEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RateEntityToRateDto implements Converter<RateEntity, Rate> {

    @Override
    public Rate convert(RateEntity source) {
        return Rate.builder()
                .id(source.getCurrency().getCurId())
                .symbol(source.getCurrency().getSymbol())
                .rate(source.getRate())
                .date(source.getDate())
                .build();
    }
}
