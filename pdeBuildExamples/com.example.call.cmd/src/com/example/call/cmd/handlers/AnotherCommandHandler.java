package com.example.call.cmd.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.ui.handlers.IHandlerService;

public class AnotherCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEvaluationContext applicationContext = (IEvaluationContext) event
				.getApplicationContext();
		IHandlerService handlerService = (IHandlerService) applicationContext
				.getVariable(IHandlerService.class.getName());
		try {
			handlerService.executeCommand(
					"com.example.cmd.commands.sampleCommand", null);
		} catch (Exception e) {
			throw new ExecutionException("Failed to run the command", e);
		}
		return null;
	}

}
