package spring.study.bookmanager.domain.converter;

import spring.study.bookmanager.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//@Converter(autoApply = true) // autoApply는 BookStatus와 같이 개발자가 직접 정의한 것일 경우에만 사용하는 것이 좋다. String, Integer, Long과 같은 것을 사용할 때에는 반드시 autoApply를 사용하지 않아야 한다. 사용할 경우 예상치 못한 컬럼까지 영향을 끼칠 수 있다. autoApply를 사용한 경우 Entity에서 사용하는 BookStatus에 빨간줄이 그어질 수 있는데, 이는 IDE가 autoApply를 인식하지 못해서 그런 것이라 생각하고 사용하는데는 문제가 없다.
@Converter
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
