package demoapp.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TracerFilter extends OncePerRequestFilter {
	
	private final Tracer tracer;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("started doFilterInternal : {}",request.getRequestURI());
		String traceId = tracer.currentTraceContext().context().traceId();
		response.setHeader("traceId", traceId);
		filterChain.doFilter(request, response);
		
		log.info("returning doFilterInternal: {}",request.getRequestURI());

	}

}
