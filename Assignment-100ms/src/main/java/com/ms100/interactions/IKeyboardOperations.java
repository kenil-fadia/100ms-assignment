package com.ms100.interactions;

public interface IKeyboardOperations {

    /**
     * Clear the existing text and type into text fields.
     *
     * @param locator
     *            {Object} - Path to text field.
     * @param value
     *            [String] - Text string to be passed/typed.
     *
     * @return {boolean} - Returns `true` if typing is successful.
     */
    boolean typeAfterClear(Object locator, String value);

    /**
     * Type into text fields.
     *
     * @param locator
     *            {Object} - Path to text field.
     * @param value
     *            [String] - Text string to be passed/typed.
     *
     * @return {boolean} - Returns `true` if typing is successful.
     */
    boolean type(Object locator, String value);

    /**
     * Press multiple keys at once.
     *
     * @param locator
     *            {Object}: Path to the text field.
     * @param keys
     *            {String}: pass the keys to be pressed at once. For e.g.
     *            keys = "ctrl+A" will select all the text
     * 
     *            Control = ctrl
     *            Shift = shift
     *            Alt = alt
     * @return
     *         {Boolean} - Returns `true` if the keypress is successful.
     */
    boolean multipleKeyStrokes(Object locator, String keys);

    /**
     * Clears the text field.
     *
     * @param locator
     *            {Object}: Path to the text field.
     * @return {boolean} - Returns `true` if clearing is successful.
     */
    public boolean clearTextField(Object locator);

    /**
     * Press multiple keys at once.
     *
     * @param locator
     *            {Object}: Path to the text field.
     * @param keys
     *            {String}: pass the keys to be pressed at once. For e.g.
     *            keys = "ctrl+A" will select all the text
     * 
     *            Control = ctrl
     *            Shift = shift
     *            Alt = alt
     * @param loopCount
     *            {Integer}: Number of times the keys are to be pressed.
     * 
     * @return
     *         {Boolean} - Returns `true` if the keypress is successful.
     */
    boolean multipleKeyStrokes(Object locator, String keys, int loopCount);
}
