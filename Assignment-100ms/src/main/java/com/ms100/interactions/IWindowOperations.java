package com.ms100.interactions;

public interface IWindowOperations {

    /**
     * Switch to a new window.
     *
     * @param windowHandle
     *            {String}
     * @return {boolean} - Return `true` if window switch operation is
     *         successful.
     */
    boolean switchToWindow(String windowHandle);

    /*
     * Get a list of all the window handles.
     * 
     * @return {String Array} - Returns a list of window handles of all the windows opened by Selenium
     */
    public String[] getAllWindowHandles();

    /**
     * Switch to a new Frame.
     *
     * @param element
     *            {String} - Path to the frame.
     * @return {boolean} - Return `true` if window switch operation is
     *         successful.
     */
    boolean switchToNewFrame(Object element);

    /**
     * Switch to a new Frame.
     *
     * @param period
     *            {int} - timeout for visibility of frame
     * @param element
     *            {String} - Path to the frame.
     * @return {boolean} - Return `true` if window switch operation is
     *         successful.
     */
    boolean switchToNewFrame(Object element, int period);

    /**
     * Switch to default window.
     *
     * @return {boolean} - Return `true` if window switch operation is
     *         successful.
     */
    boolean switchToDefault();

    /**
     * Maximize the window.
     *
     * @return {boolean} - Return `true` is maximize operation is successful.
     */
    boolean maximizeWindow();

    /**
     * Refresh the window.
     *
     * @return {boolean} - Return `true` if window refresh operation is
     *         successful.
     */
    boolean refresh();

    /**
     * Navigate Back
     *
     * @return {boolean} - Return `true` if navigation is successful.
     */
    boolean navigateBack();

    /**
     * Open URL.
     *
     * @param url
     *            {String}
     * @return {boolean} - Return `true` if URL is opened successfully.
     */
    boolean open(String url);

    /**
     * Set the size of the browser window.
     *
     * @param width
     *            {int} - Width of the browser window.
     * @param height
     *            {int} - Height of the browser window.
     *
     * @return {boolean} - Returns `true` if browser window size is set.
     */
    boolean setSize(int width, int height);

    /**
     * Get the size of browser window.
     *
     * @return {Object} - Returns the dimension of the browser window.
     */
    Object getSize();

    /**
     * Inject simulations
     */
    boolean injectSimulations();

    /**
     * Injects mouse and keyboard simulations in case they are not available.
     */
    public void checkAndInjectSimulations();

    /**
     * Close the active window.
     *
     * @return {boolean} - Returns `true` if window is closed successfully.
     */
    boolean closeWindow();

    /**
     * Return the handle of the currently active window / tab
     *
     * @return {String}
     */
    String getCurrentWindowHandle();
}
