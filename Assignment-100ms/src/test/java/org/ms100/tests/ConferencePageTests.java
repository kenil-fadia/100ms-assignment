package org.ms100.tests;

import io.qameta.allure.Story;
import org.ms100.common.EnvironmentSetup;
import org.ms100.pages.ConferencePage;
import org.ms100.pages.JoinPage;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class ConferencePageTests extends EnvironmentSetup {

    private final String conferenceUrl = "https://keniltestasiignment.app.100ms.live/meeting/ksq-lza-kce";

    public static final String user1 = "Test User 1";
    public static final String user2 = "Test User 2";

    JoinPage joinPage;
    ConferencePage conferencePage;

    String parentWindowHandle, newWindowHandle;

    @BeforeClass
    public void setup() {
        parentWindowHandle = windowHandler.getCurrentWindowHandle();
        joinPage = new JoinPage();
        conferencePage = new ConferencePage();
    }

    @Test
    @Story("Join a conference")
    public void joinConference() {
        windowHandler.open(conferenceUrl);
        joinPage.waitForPageLoad();
        joinPage.joinConference(user1);
    }

    @Test(priority = 1)
    @Story("Test the change role functionality")
    public void changeRole() {
        conferencePage.changeRole("guest");
        conferencePage.asserRoleChange();
    }

    @Test(
            priority = 2,
            dependsOnMethods = "joinConference"
    )
    @Story("Testing if audio player is working")
    public void testAudioPlayer() {
        conferencePage.waitForPageLoad();
        conferencePage.playAudio("Audio1");
        conferencePage.assertAudioPlayerOpen();
    }

    @Test(
            priority = 3,
            dependsOnMethods = "testAudioPlayer"
    )
    @Story("Testing if video player is working")
    public void testVideoPlayer() {
        conferencePage.waitForPageLoad();
        conferencePage.playVideo("100ms-360P");
        conferencePage.assertVideoPlayerOpen();
    }

    @Test(
            priority = 4,
            dependsOnMethods = "testVideoPlayer"
    )
    @Story("Test if screen share is working fine")
    public void testScreenShare() {
        // open a new tab and switch .
        driver.switchTo().newWindow(WindowType.WINDOW);
        newWindowHandle = windowHandler.getCurrentWindowHandle();
        conferencePage.switchToTab(newWindowHandle);
        windowHandler.open(conferenceUrl);
        joinPage.waitForPageLoad();
        joinPage.joinConference(user2);
        conferencePage.screenShare();
        conferencePage.assertScreenShareStarted();

        conferencePage.switchToTab(parentWindowHandle);
        conferencePage.assertSharedScreenVisibleForOtherUsers();

        conferencePage.switchToTab(newWindowHandle);
        conferencePage.stopScreenShare();
    }

    @Test(
            priority = 5,
            dependsOnMethods = "testVideoPlayer"
    )
    @Story("Test if the PeerList shows all the joined users")
    public void testPeerList() {
        HashMap<String, String> peers = conferencePage.getPeerList();

        String actualUser1 = peers.get("peer1");
        String actualUser2 = peers.get("peer2");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue((user1.equals(actualUser1) || user1.equals(actualUser2)));
        softAssert.assertTrue((user2.equals(actualUser1) || user2.equals(actualUser2)));

        softAssert.assertAll();

        conferencePage.closePeerList();
    }

    @Test(
            priority = 6,
            dependsOnMethods = "testVideoPlayer"
    )
    @Story("Test if the chat feature is working as expected")
    public void testChatFunctionality() {
        String message = "HelloWorld";
        conferencePage.switchToTab(parentWindowHandle);
        conferencePage.sendChatMessage(message);

        conferencePage.switchToTab(newWindowHandle);
        conferencePage.assertIfChatVisibleToEveryone(message);
    }
}
