package podamEx;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.List;

public class DateTimeStrategy implements AttributeStrategy<LocalDateTime> {
    @Override
    public LocalDateTime getValue(Class<?> aClass, List<Annotation> list) {
        return LocalDateTime.now();
    }
}
