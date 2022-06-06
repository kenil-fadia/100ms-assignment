package com.ms100.interactions;

public interface IMouseOperations {

    /**
     * Performs a click operation on the element.
     *
     * @param locator
     *            {Object} - Path to the element to be clicked.
     *
     * @return {boolean} - returns `true` if click operation is successful.
     */
    boolean click(Object locator);

    /**
     * Performs a double-click operation on the element.
     *
     * @param locator
     *            {Object} - Path to the element to be double-clicked.
     *
     * @return {boolean} - returns `true` if double-click operation is
     *         successful.
     */
    boolean doubleClick(Object locator);

    /**

     * Performs a double-click operation on the element.
     *
     * @param locator
     *            {Object} - Path to the element to be double-clicked.
     *
     * @return {boolean} - returns `true` if double-click operation is
     *         successful.
     */
    boolean doubleClickUsingCode(Object locator);

    /**
     * Performs a mouse-over operation on the element.
     *
     * @param locator
     *            {Object} - Path to the element.
     *
     * @return {Object} - Returns a Mouse pointer positioning object. Returns
     *         `false` if mouse-over operation is not successful.
     */
    Object mouseOver(Object locator);

    /**
     * Performs a click operation on all the element located by the path
     * specified by `locator`.
     *
     * @param locator
     *            {Object} - Path to the element.
     *
     * @return {boolean} - returns `true` if click operation is successful.
     */
    boolean clickAllElementsLocatedBy(Object locator);

    /**
     * Scrolls the page such that the element is now in the view port.
     *
     * @param locator
     *            {Object} - Path to the element.
     *
     * @return {boolean} - returns `true` if scroll operation is successful.
     */
    boolean scroll(Object locator);

    /**
     * Trigger mouse down event on the element.
     * 
     * @param locator
     *            {Object} - Path to the element.
     * @return {boolean} - Returns `true` if mouse over was successful, else `false`.
     */
    boolean mouseDown(Object locator);

    /**
     * Trigger mouse down event on the element at `x` and `y` offset.
     * 
     * @param locator
     *            {Object} - Path to the element.
     * @param xOffset
     *            {int}
     * @param yOffset
     *            {int}
     * @return {boolean} - Returns `true` if mouse over was successful, else `false`.
     */
    boolean mouseDown(Object locator, int xOffset, int yOffset);

    /**
     * Trigger mouse move event.
     * 
     * @param locator
     *            {Object} - Path to the element.
     * @param xOffset
     *            {int}
     * @param yOffset
     *            {int}
     * @return {boolean} - Returns `true` if mouse move is successful, else `false`.
     */
    boolean mouseMove(Object locator, int xOffset, int yOffset);

    /**
     * Trigger mouse up event.
     * 
     * @param locator
     *            {Object} - Path to the element
     * @return {boolean} - Returns `true` if mouse up is successful, else false.
     */
    boolean mouseUp(Object locator);

    /**
     * Scrolls the page by an offset.
     *
     * @param xVal
     *            {int} - Offset along the X-Axis.
     * @param yVal
     *            {int} - Offset along the Y-Axis.
     *
     * @return {boolean} - returns `true` if scroll operation is successful.
     */
    boolean scroll(int xVal, int yVal);

    /**
     * Clicks on the element found using findElements.
     *
     * @param locator
     *            {Object} - Path to the elements.
     * @param index
     *            {int} - index number of the element to be clicked.
     *
     * @return {boolean} - returns `true` if click operation is successful.
     */
    boolean listClick(Object locator, int index);

    /**
     * Performs a context click on the specified element
     * 
     * @param locator
     *            {Object} - Path to the element.
     * @return {boolean} - returns `true` if context click operation is successful.
     */
    boolean contextClick(Object locator);


    /**
     * Performs a click operation on the element.
     * 
     * @param locator
     *            {Object} - Path to the element.
     * @param ctrlKey
     *            {boolean} - Press control key if `true`
     * @param altKey
     *            {boolean} - Press alt key if `true`
     * @param shiftKey
     *            {boolean} - Press shift key if `true`
     * @param metaKey
     *            {boolean} - Press meta key if `true`
     * @return {boolean} - returns `true` if click operation is successful.
     */
    boolean click(Object locator, boolean ctrlKey, boolean altKey, boolean shiftKey, boolean metaKey);
}
