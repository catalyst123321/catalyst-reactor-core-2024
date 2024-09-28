package org.springframework.catalyst.reactor.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventProxy {

    private static final Logger logger = LoggerFactory.getLogger(EventProxy.class);



    public static Event generalEvent(Event event) {

        return (obj)-> {
            Object res = null;
            try {
                res = null;
                logger.info("event start {}");

                res = event.apply(obj);

                logger.info("event end {}");
            } catch (Exception e) {
                logger.error("event failed {} {}", event, obj);
            }

            return res;
        };

    }

    public static Event generalEvent(Event event, ExceptionEvent exceptionEvent) {

        return (obj)-> {
            Object res = null;
            try {
                res = null;
                logger.info("event start {}");

                res = event.apply(obj);

                logger.info("event end {}");
            } catch (Exception e) {
                try {
                    res = exceptionEvent.exceptionHandle(e, obj);
                } catch (Exception ex) {
                    logger.error("exceptionEvent.exceptionHandle(e, obj) failed {} {}", event, obj);
                }
                logger.error("event failed {} {}", event, obj);
            }

            return res;
        };

    }


    public static Event eventWithPreCheck(Event event, CheckEvent checkEvent, int checkRetryTimes, int intervalSec) {

        return (obj) -> {
            Object res = null;
            try {
                res = null;
                logger.info("event start {}");


                for (int i = 0; i < checkRetryTimes; i++) {
                    logger.debug("eventWithPreCheck retries {}",i);
                    if (checkEvent.check(obj)) {
                        res = event.apply(obj);
                        break;
                    }

                    Thread.sleep(intervalSec * 1000);
                }


                logger.info("event end {}");
            } catch (Exception e) {
                logger.error("event failed {} {}", event, obj);
            }

            return res;
        };

    }

    public static Event eventWithAfterCheck(Event event, CheckEvent checkEvent, int checkRetryTimes, int intervalSec) {

        return (obj) -> {
            Object res = null;
            try {
                res = null;
                logger.info("event start {}");

                res = event.apply(obj);

                boolean checkPass = false;
                for (int i = 0; i < checkRetryTimes; i++) {
                    logger.debug("eventWithPreCheck retries {}",i);

                    if (checkEvent.check(obj)) {
                        checkPass = true;
                        break;
                    }

                    Thread.sleep(intervalSec * 1000);
                }

                if (!checkPass) {
                    res = null;
                    logger.error("eventWithAfterCheck failed {} {}", event, obj);
                }


                logger.info("event end {}");
            } catch (Exception e) {
                logger.error("event failed {} {}", event, obj);
            }

            return res;
        };


    }

}
