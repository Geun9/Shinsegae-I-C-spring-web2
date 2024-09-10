package todo.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import todo.dao.MemberDAO;
import todo.dao.TodoDAO;
import todo.domain.MemberVO;
import todo.domain.TodoVO;
import todo.dto.MemberDTO;
import todo.dto.TodoDTO;
import todo.util.ModelUtil;

@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {

        dao = new MemberDAO();   //직접 dao 주입
        modelMapper = ModelUtil.INSTANCE.get();
    }


      public MemberDTO login(MemberDTO member) throws Exception{

            log.info("... getWithPassword Service ...");
            MemberVO memberVO = dao.getWithPassword(member);
            MemberDTO dto = modelMapper.map(memberVO,MemberDTO.class);

          return dto;
      }

}


