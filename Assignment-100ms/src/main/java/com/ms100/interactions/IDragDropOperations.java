package com.ms100.interactions;

public interface IDragDropOperations {

    /**
     * Drag an Element from `source` to `target`.
     *
     * @param source
     *            {Object} - Drag an Element present at the `source`.
     * @param target
     *            {Object} - Drag the Element to the `target`.
     *
     * @return {boolean} - returns `true` if drag operation is successful.
     */
    public boolean dragDrop(Object source, Object target);

    /**
     * Drag an Element from `source` to `target`.
     * (Uses jquery-simulations, github url: https://github.com/j-ulrich/jquery-simulate-ext)
     *
     * @param source
     *            {Object} - Drag an Element present at the `source`.
     * @param target
     *            {Object} - Drag the Element to the `target`.
     *
     * @return {boolean} - returns `true` if drag operation is successful.
     */
    public boolean dragDropUsingCode(Object sourceLocator, Object targetLocator);

    /**
     * Move an Element at `source` by `xOffset` along the X-Axis and by `yOffset` along the Y-Axis.
     *
     * @param source
     *            {Object} - Drag an Element present at the `source`.
     * @param xOffset
     *            {int} - Offset along the X-Axis.
     * @param yOffset
     *            {int} - Offset along the Y-Axis.
     *
     * @return {boolean} - returns `true` if drag operation is successful.
     */
    public boolean dragDrop(Object source, int xOffset, int yOffset);

    /**
     * Move an Element at `source` by `xOffset` along the X-Axis and by `yOffset` along the Y-Axis.
     * (Uses jquery-simulations, github url: https://github.com/j-ulrich/jquery-simulate-ext)
     *
     * @param source
     *            {Object} - Drag an Element present at the `source`.
     * @param xOffset
     *            {int} - Offset along the X-Axis.
     * @param yOffset
     *            {int} - Offset along the Y-Axis.
     *
     * @return {boolean} - returns `true` if drag operation is successful.
     */
    public boolean dragDropUsingCode(Object sourceLocator, int xOffset, int yOffset);
}
