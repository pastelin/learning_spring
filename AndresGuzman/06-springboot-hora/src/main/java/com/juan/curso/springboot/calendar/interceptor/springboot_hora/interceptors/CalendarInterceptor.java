package com.juan.curso.springboot.calendar.interceptor.springboot_hora.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor {

	@Value("${config.calendar.open}")
	private Integer open;

	@Value("${config.calendar.close}")
	private Integer close;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);

		if (hour >= open && hour < close) {
			StringBuilder message = new StringBuilder("Bienvenidos al horario de atención a clientes");
			message.append(", atendemos desde las ");
			message.append(open);
			message.append("hrs.");
			message.append(" hasta las ");
			message.append(close);
			message.append("hrs.");
			message.append(" Gracias por su visita!");
			request.setAttribute("message", message.toString());

			return true;
		}

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> data = new HashMap<>();
		StringBuilder message = new StringBuilder("Cerrado, fuera de atención ");
		message.append("por favor visitenos desde las ");
		message.append(open);
		message.append(" y las ");
		message.append(close);
		message.append("hrs. Gracias!");
		data.put("message", message.toString());
		data.put("date", new Date().toString());

		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().write(mapper.writeValueAsString(data));

		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("CalendarInterceptor.afterCompletion()...");
	}

}
