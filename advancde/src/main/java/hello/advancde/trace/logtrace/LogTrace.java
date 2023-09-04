package hello.advancde.trace.logtrace;

import hello.advancde.trace.TraceStatus;

public interface LogTrace {

	TraceStatus begin(String message);

	void end(TraceStatus status);

	void exception(TraceStatus status, Exception e);
}
