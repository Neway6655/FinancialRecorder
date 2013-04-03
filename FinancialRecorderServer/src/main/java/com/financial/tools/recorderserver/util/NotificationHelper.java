package com.financial.tools.recorderserver.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class NotificationHelper {

	private static final String GCM_MESSAGE_TITLE_KEY = "title";

	private static final String GCM_MESSAGE_BODY_KEY = "message";

	private static final String GCM_KEY = "AIzaSyDm5fOxjtm38z6oSxdzlwttQo0clbrKFC4";

	private static final int RETRY_TIMES = 3;

	private static Logger logger = LoggerFactory.getLogger(NotificationHelper.class);

	public static void sendNotification(String deviceRegId, String notificationTitle, String notificationMessage) {
		Message message = new Message.Builder().addData(GCM_MESSAGE_TITLE_KEY, notificationTitle)
				.addData(GCM_MESSAGE_BODY_KEY, notificationMessage).build();
		Sender sender = new Sender(GCM_KEY);
		if (StringUtils.isEmpty(deviceRegId)) {
			logger.debug("deviceRegId:{} doesn't exist.", deviceRegId);
			return;
		}

		try {
			Result result = sender.send(message, deviceRegId, RETRY_TIMES);
			logger.debug("notification send to deviceRegId: {}, result: {}.", deviceRegId, result);
		} catch (IOException e) {
			logger.error("Can't send notification to deviceRegId: {}", deviceRegId);
		}
	}

}
