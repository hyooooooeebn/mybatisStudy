/*======================
 StudentController.java 
========================*/

package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController
{
	//SqlSession 을 활용하여 마이바티스 객체 의존성 (자동) 주입
	@Autowired
	private SqlSession sqlSession;
	
	// ※ 매개변수를 등록하는 과정에서 매개변수 목록에 적혀있는
	//	  클래스의 객체 정보는 스프링이 제공한다.
	
	// ※ 사용자의 요청 주소와 메소드를 매핑하는 과정 필요
	//	  @RequestMapping(value = "요청 주소", method = 전송방식)』
	//	  이 때, 전송 방식은 submit 액션으로 처리되는 경우만 『POST』로 처리하고
	//    나머지는 모든 전송 방식은 GET 으로 처리한다.
	
	   // 학생 리스트(목록)
	   @RequestMapping(value = "/studentlist.action", method = RequestMethod.GET)
	   public String studentList(Model model)
	   {
	      String result = "/WEB-INF/view/";
	      
	      IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
	      
	      model.addAttribute("count", dao.count());
	      model.addAttribute("list", dao.list());
	      
	      result += "StudentList.jsp";
	      
	      return result;
	   }
	
	// 학생 데이터 입력 폼
	@RequestMapping(value = "/studentinsertform.action", method = RequestMethod.GET)
	public String studentInsertForm()
	{
		String result = null;
		
		result = "StudentInsertList";
		/* result = "/WEB-INF/view/StudentInsertList"; */
				
		return result;
	}
	
	// 학생 데이터 입력 액션
	@RequestMapping(value = "/studentinsert.action", method = RequestMethod.GET)
	public String studentInsert(StudentDTO student)
	{
		String result = null;
		
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		dao.add(student);
		result = "redirect:studentlist.action";
		
		return result;
	}
}
