package pi.enset.DTO.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GeneralMapper<f,t> {
    public t fromOrigin(f from) {
        t to = null;
        BeanUtils.copyProperties(from, to);
        return to;
    }

    public f fromRequestDTO(t from) {
        f to = null;
        BeanUtils.copyProperties(from, to);
        return to;
    }
}
