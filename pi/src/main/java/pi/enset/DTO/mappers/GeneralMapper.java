package pi.enset.DTO.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralMapper<F, T> {
    private final Class<T> targetT;
    private final Class<F> targetF;

    public GeneralMapper(
            @Value("#{T(java.lang.Object).Class}") Class<T> target,
            @Value("#{T(java.lang.Object).Class}") Class<F> targetF) {

        this.targetF = targetF;
        this.targetT = target;
    }

    public T fromOrigin(F from) {
        T to = null;
        try {
            to = targetT.getDeclaredConstructor().newInstance();
        } catch (Exception ignored) {

        }
        BeanUtils.copyProperties(from, to);
        return to;
    }

    public F fromRequestDTO(T from) {
        F to = null;
        try {
            to = targetF.getDeclaredConstructor().newInstance();
        } catch (Exception ignored) {

        }
        BeanUtils.copyProperties(from, to);
        return to;
    }
}

