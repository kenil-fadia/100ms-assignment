package org.ms100.pages;

import io.qameta.allure.Step;
import org.ms100.common.EnvironmentSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.ms100.common.AllureTestNGListener.logFailure;

public class ConferencePage extends EnvironmentSetup {

    @FindBy(css = "[data-testid = 'first_person_img']")
    WebElement first_person_img;

    @FindBy(css = "[data-testid='screenshare_audio']")
    WebElement screenShareAudio;

    @FindBy(css = "[data-testid='audio_playlist']")
    WebElement audioPlaylist;

    @FindBy(xpath = "//p[text() = 'Audio Player']")
    WebElement audioPlayerMenuTitle;

    @FindBy(css = "[data-testid='video_playlist']")
    WebElement videoPlaylist;

    @FindBy(xpath = "//p[text() = 'Video Playlist']")
    WebElement videoPlayerMenuTitle;

    @FindBy(css = "[role = 'menuitem']")
    List<WebElement> audioVideoPlayerMenuItems;

    @FindBy(css = "[role='menu'] [data-testid='playlist_prev_btn']")
    WebElement audioPlaylistPrevButton;

    @FindBy(css = "[role='menu'] [data-testid='playlist_play_pause_btn']")
    WebElement audioPlaylistPlayPauseButton;

    @FindBy(css = "[role='menu'] [data-testid='playlist_next_btn']")
    WebElement audioPlaylistNextButton;

    @FindBy(xpath = "//p[text() = 'Audio Player']//following-sibling::button")
    WebElement audioPlayerMenuCloseButton;

    @FindBy(css = "[data-testid='conferencing'] [data-testid='playlist_prev_btn']")
    WebElement  videoPlaylistPrevButton;

    @FindBy(css = "[data-testid='conferencing'] [data-testid='playlist_play_pause_btn']")
    WebElement videoPlaylistPlayPauseButton;

    @FindBy(css = "[data-testid='conferencing'] [data-testid='playlist_next_btn']")
    WebElement videoPlaylistNextButton;

    @FindBy(css = "[data-testid = 'videoplaylist_cross_btn']")
    WebElement videoPlayerCloseButton;

    @FindBy(css = "[data-testid = 'screen_share_btn']")
    WebElement screenShareButton;

    @FindBy(css = "[data-testid = 'stop_screen_share_btn']")
    WebElement stopScreenShareButton;

    @FindBy(css = "[data-testid = 'screenshare_tile']")
    WebElement screenShareTile;

    @FindBy(css = "[data-testid='participant_list']")
    WebElement peerListButton;

    @FindBy(css = "[data-testid ^= 'participant_'][role = 'menuitem']")
    List<WebElement> peers;

    @FindBy(css = "button[data-testid = 'chat_btn']:nth-child(3)")
    WebElement chatButton;

    @FindBy(css = "textarea[placeholder='Write something here']")
    WebElement chatTextField;

    @FindBy(xpath = "//textarea/following-sibling::button")
    WebElement chatSendButton;

    @FindBy(css = "select")
    WebElement changeRoleDropdown;

    @FindBy(xpath = "//button[text() = 'Confirm']")
    WebElement changeRoleConfirmButton;

    @FindBy(xpath = "//*[text() = 'You are now a guest']")
    WebElement feedbackMessage;

    @FindBy(xpath = "//*[contains(text(), 'New message from')]")
    WebElement chatFeedbackMessage;

    public ConferencePage() {
        PageFactory.initElements(factory, this);
    }

    @Step("Waiting for the Conference Page to load")
    public void waitForPageLoad() {
        waitHandler.waitForPageLoad();
        waitHandler.waitForVisibilityOf(screenShareAudio);
        waitHandler.waitForVisibilityOf(audioPlaylist);
        waitHandler.waitForVisibilityOf(videoPlaylist);
        waitHandler.waitForVisibilityOf(screenShareButton);
        waitHandler.waitForVisibilityOf(chatButton);
    }

    @Step("Playing from the audio playlist")
    public void playAudio(String audioName) {
        mouseHandler.click(audioPlaylist);
        PageFactory.initElements(factory, this);
        waitHandler.waitForVisibilityOf(audioPlayerMenuTitle);
        for (int i = 0; i < audioVideoPlayerMenuItems.size(); i++) {
            if(
                    audioName.equals(queryHandler.getText(
                            queryHandler.findElementWithinElement(audioVideoPlayerMenuItems.get(i),
                                    By.xpath("//p[text() = '" + audioName + "']")
                            )
                    ))
            ) {
                mouseHandler.click(audioVideoPlayerMenuItems.get(i));
                break;
            }
        }
    }

    @Step("Playing from the video playlist")
    public void playVideo(String videoName) {
        mouseHandler.click(videoPlaylist);
        PageFactory.initElements(factory, this);
        waitHandler.waitForVisibilityOf(videoPlayerMenuTitle);
        for (int i = 0; i < audioVideoPlayerMenuItems.size(); i++) {
            if(
                    videoName.equals(queryHandler.getText(
                            queryHandler.findElementWithinElement(audioVideoPlayerMenuItems.get(i),
                                    By.xpath("//p[text() = '" + videoName + "']")
                            )
                    ))
            ) {
                mouseHandler.click(audioVideoPlayerMenuItems.get(i));
                break;
            }
        }
    }

    @Step("Asserting Audio Player Visibility")
    public void assertAudioPlayerOpen() {
        SoftAssert softAssert = new SoftAssert();
        try {
            softAssert.assertTrue(verificationsHandler.isElementDisplayed(audioPlaylistPrevButton));
            softAssert.assertTrue(verificationsHandler.isElementDisplayed(audioPlaylistPlayPauseButton));
            softAssert.assertTrue(verificationsHandler.isElementDisplayed(audioPlaylistNextButton));
            softAssert.assertAll();
            mouseHandler.click(audioPlayerMenuCloseButton);
        } catch (AssertionError e) {
            e.printStackTrace();
            logFailure("Video Player is not visible " + Arrays.toString(e.getStackTrace()));
        }
    }

    @Step("Asserting Video Player Visibility")
    public void assertVideoPlayerOpen() {
        SoftAssert softAssert = new SoftAssert();
        try {
            softAssert.assertTrue(verificationsHandler.isElementDisplayed(videoPlaylistPrevButton));
            softAssert.assertTrue(verificationsHandler.isElementDisplayed(videoPlaylistPlayPauseButton));
            softAssert.assertTrue(verificationsHandler.isElementDisplayed(videoPlaylistNextButton));
            softAssert.assertAll();
            mouseHandler.click(videoPlayerCloseButton);
        } catch (AssertionError e) {
            e.printStackTrace();
            logFailure("Video Player is not visible " + Arrays.toString(e.getStackTrace()));
        }
    }

    public void screenShare() {
        mouseHandler.click(screenShareButton);
    }

    public void assertScreenShareStarted() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(waitHandler.waitForVisibilityOf(stopScreenShareButton));
        softAssert.assertAll();
    }

    public void switchToTab(String tab) {
        windowHandler.switchToWindow(tab);
        PageFactory.initElements(factory, this);
    }

    public void assertSharedScreenVisibleForOtherUsers() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(waitHandler.waitForVisibilityOf(screenShareTile));
        softAssert.assertAll();
    }

    public void stopScreenShare() {
        mouseHandler.click(stopScreenShareButton);
    }

    public HashMap<String, String> getPeerList() {
        mouseHandler.click(peerListButton);
        HashMap<String, String> peerList = new HashMap<String, String>();

        for(int i = 0; i < peers.size(); i++) {
            peerList.put("peer" + (i + 1), queryHandler.getText(queryHandler.findElementWithinElement(peers.get(i), By.cssSelector("p"))));
        }

        return peerList;
    }

    public void sendChatMessage(String message) {
        mouseHandler.click(chatButton);
        keyboardHandler.typeAfterClear(chatTextField, message);
        mouseHandler.click(chatSendButton);
    }

    public void assertIfChatVisibleToEveryone(String message) {
        waitHandler.waitForInvisibilityOf(chatFeedbackMessage);
        mouseHandler.click(chatButton);
        waitHandler.waitForVisibilityOf(chatSendButton);
        WebElement chatMessage = (WebElement) queryHandler.findElement(By.xpath("//p[contains(text(), '" + message + "')]"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(waitHandler.waitForVisibilityOf(chatMessage));
        softAssert.assertAll();
    }

    public void changeRole(String roleName) {
        mouseHandler.click(peerListButton);
        mouseHandler.click(queryHandler.findElementWithinElement(peers.get(0), By.cssSelector("button")));
        dropDownHandler.selectOptionByText(changeRoleDropdown, roleName);
        mouseHandler.click(changeRoleConfirmButton);
    }

    public void asserRoleChange() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(queryHandler.getText(feedbackMessage), "You are now a guest");
        softAssert.assertAll();
    }

    public void closePeerList() {
        mouseHandler.click(peerListButton);
    }
}
