window.onload = function () {
    dwr.engine.setActiveReverseAjax(true);
    dwr.engine.setErrorHandler(errorHandler);
    dwr.engine.setPollStatusHandler(updatePollStatus);
}

function errorHandler(message, ex) {
    dwr.util.setValue("errorDisplay", "Cannot connect to server. " + message, {escapeHtml: false});

    /* clear error after 5 sec */
    setTimeout(
        function () {
            dwr.util.setValue("errorDisplay", "");
        },
        5000
    )
}

function updatePollStatus(pollStatus) {
    dwr.util.setValue("pollStatusDisplay", pollStatus ? "Online" : "Offline", {escapeHtml: false});
}

updateClockDisplay = function (time) {
    dwr.util.setValue("clockDisplay", time);
}
