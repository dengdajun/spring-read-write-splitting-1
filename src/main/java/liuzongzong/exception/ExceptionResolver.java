package liuzongzong.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import liuzongzong.util.BizzException;
import liuzongzong.util.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by liuyazong on 16/4/29 上午10:33.
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");

            try(PrintWriter writer = response.getWriter()){
                String exMsg =null;
                if(ex instanceof BizzException){
                    exMsg = objectMapper.writeValueAsString(new ResponseEntity.Builder<Object>().failed().flag(((BizzException) ex).getFlag()).msg(ex.getMessage()).build());
                }else {
                    exMsg = objectMapper.writeValueAsString(new ResponseEntity.Builder<Object>().failed().msg(ex.getMessage()).build());
                }

                writer.write(exMsg);
                writer.flush();
                writer.close();
            }catch (Exception e){}
        } catch (Exception e) {
        }

        return null;
    }
}
