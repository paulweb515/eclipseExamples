package org.eclipse.e4.example.addon;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuContribution;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class MenuContributionProcessingAddon {
	@Inject
	private MApplication application;
	@Inject
	private IEventBroker broker;
	
	private EventHandler menuContributionHandler;
	
	@PostConstruct
	public void init() {
		registerModelListeners();
	}
	
	private void registerModelListeners() {
		menuContributionHandler = new EventHandler() {

			@Override
			public void handleEvent(Event event) {
				if (application == event.getProperty(UIEvents.EventTags.ELEMENT)) {
					final Object eventType = event.getProperty(UIEvents.EventTags.TYPE);
					if (UIEvents.EventTypes.ADD.equals(eventType)) {
						Object obj = event.getProperty(UIEvents.EventTags.NEW_VALUE);
						if (obj instanceof MMenuContribution) {
							processContribution((MMenuContribution)obj);
						}
					} else if ("UNKNOWN".equals(eventType)) {
						for (MMenuContribution c : application.getMenuContributions()) {
							processContribution(c);
						}
					}
				}
			}
		};
		broker.subscribe(UIEvents.MenuContributions.TOPIC_MENUCONTRIBUTIONS, menuContributionHandler);
	}
	
	protected void processContribution(MMenuContribution mc) {
		if ("help".equals(mc.getParentId())) {
			if (!mc.getChildren().isEmpty()) {
				final MMenuElement element = mc.getChildren().get(0);
				if ("org.eclipse.equinox.p2.ui.sdk.update".equals(element.getElementId())) {
					// get rid of the MMenuContribuition that contains the 
					// Check for New Updates menu item
					mc.setToBeRendered(false);
				}
			}
		}
	}

	@PreDestroy
	public void dispose() {
		unregsiterModelListeners();
	}
	
	private void unregsiterModelListeners() {
		broker.unsubscribe(menuContributionHandler);
	}
}
