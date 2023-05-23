package by.fin.module.converters;

import by.fin.module.core.dto.Weekend;
import by.fin.module.entity.WeekendEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WeekendEntityToDto implements Converter<WeekendEntity, Weekend> {

    @Override
    public Weekend convert(WeekendEntity source) {
        return Weekend.builder()
                .weekendId(source.getWeekendId())
                .calendarDate(source.getCalendarDate())
                .isDayOff(source.isDayOff())
                .build();
    }
}
