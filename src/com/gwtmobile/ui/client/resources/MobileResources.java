package com.gwtmobile.ui.client.resources;



import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


public interface MobileResources extends ClientBundle {

	public static final MobileResources INSTANCE =  GWT.create(MobileResources.class);
	
	public enum StyleVariants { None, Var1, Var2, Var3, Var4, Var5};
	
	public enum IconImages { None, ArrowLeft, ArrowRight, ArrowUp, ArrowDown, Delete, Plus, Minus, Check, Gear, Refresh, Forward, Back, Grid, Star, Alert, Info, Home, Search };

	@Source("icons/clear.cache.gif") ImageResource iconClearCache();
	
	@Source("icons/alarm.png") ImageResource iconAlarm();
	@Source("icons/arrowdown.png") ImageResource iconArrowDown();
	@Source("icons/arrowdown2.png") ImageResource iconArrow2Down();
	@Source("icons/arrowleft.png") ImageResource iconArrowLeft();
	@Source("icons/arrowleft2.png") ImageResource iconArrow2Left();
	@Source("icons/arrowright.png") ImageResource iconArrowRight();
	@Source("icons/arrowright2.png") ImageResource iconArrow2Right();
	@Source("icons/arrowup.png") ImageResource iconArrowUp();
	@Source("icons/arrowup2.png") ImageResource iconArrow2Up();
	@Source("icons/bank.png") ImageResource iconBank();
	@Source("icons/briefcase.png") ImageResource iconBriefcase();
	@Source("icons/calculator.png") ImageResource iconCalculator();
	@Source("icons/camera.png") ImageResource iconCamera();
	@Source("icons/clapboard.png") ImageResource iconClapboard();
	@Source("icons/clock.png") ImageResource iconClock();
	@Source("icons/connections.png") ImageResource iconConnections();
	@Source("icons/credit_card.png") ImageResource iconCreditCard();
	@Source("icons/database.png") ImageResource iconDatabase();
	@Source("icons/delete.png") ImageResource iconDelete();
	@Source("icons/delivery.png") ImageResource iconDelivery();
	@Source("icons/disk.png") ImageResource iconDisk();
	@Source("icons/eject.png") ImageResource iconEject();
	@Source("icons/fastforward.png") ImageResource iconFastforward();
	@Source("icons/filter.png") ImageResource iconFilter();
	@Source("icons/fire.png") ImageResource iconFire();
	@Source("icons/first_aid.png") ImageResource iconFirstAid();
	@Source("icons/folder.png") ImageResource iconFolder();
	@Source("icons/forwardtoend.png") ImageResource iconForwardToEnd();
	@Source("icons/games.png") ImageResource iconGames();
	@Source("icons/graph.png") ImageResource iconGraph();
	@Source("icons/heart.png") ImageResource iconHeart();
	@Source("icons/house.png") ImageResource iconHouse();
	@Source("icons/lightbulb.png") ImageResource iconLightBulb();
	@Source("icons/magnet.png") ImageResource iconMagnet();
	@Source("icons/magnifyingglass.png") ImageResource iconMagnifyingGlass();
	@Source("icons/mailclosed.png") ImageResource iconMailClosed();
	@Source("icons/mailopened.png") ImageResource iconMailOpened();
	@Source("icons/man.png") ImageResource iconMan();
	@Source("icons/minus.png") ImageResource iconMinus();
	@Source("icons/monitor.png") ImageResource iconMonitor();
	@Source("icons/move.png") ImageResource iconMove();
	@Source("icons/newspaper.png") ImageResource iconNewspaper();
	@Source("icons/padlock.png") ImageResource iconPadlock();
	@Source("icons/page.png") ImageResource iconPage();
	@Source("icons/pause.png") ImageResource iconPause();
	@Source("icons/pencilangled.png") ImageResource iconPencilAngled();
	@Source("icons/pencilstraight.png") ImageResource iconPencilStraight();
	@Source("icons/photos.png") ImageResource iconPhotos();
	@Source("icons/piggy.png") ImageResource iconPiggy();
	@Source("icons/play.png") ImageResource iconPlay();
	@Source("icons/plus.png") ImageResource iconPlus();
	@Source("icons/preferences.png") ImageResource iconPreferences();
	@Source("icons/record.png") ImageResource iconRecord();
	@Source("icons/refresh.png") ImageResource iconRefresh();
	@Source("icons/reload.png") ImageResource iconReload();
	@Source("icons/rewind.png") ImageResource iconRewind();
	@Source("icons/rewindtostart.png") ImageResource iconRewindToStart();
	@Source("icons/rss.png") ImageResource iconRss();
	@Source("icons/safe.png") ImageResource iconSafe();
	@Source("icons/scales.png") ImageResource iconScales();
	@Source("icons/shoppingcart.png") ImageResource iconShoppingCart();
	@Source("icons/speaker.png") ImageResource iconSpeaker();
	@Source("icons/speechbubble.png") ImageResource iconSpeechBubble();
	@Source("icons/speechmedia.png") ImageResource iconSpeechMedia();
	@Source("icons/star.png") ImageResource iconStar();
	@Source("icons/trash.png") ImageResource iconTrash();
	@Source("icons/trend.png") ImageResource iconTrend();
	@Source("icons/world.png") ImageResource iconWorld();
	
	public static Map<String, ImageResource> IMAGE_MAP= new HashMap<String, ImageResource>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = -6313663272722299710L;

		{
			put(IconImages.None.toString(), MobileResources.INSTANCE.iconClearCache());
            put(IconImages.Alert.toString(), MobileResources.INSTANCE.iconAlarm());
            put(IconImages.ArrowDown.toString(), MobileResources.INSTANCE.iconArrowDown());
            put(IconImages.ArrowLeft.toString(), MobileResources.INSTANCE.iconArrowLeft());
            put(IconImages.ArrowRight.toString(), MobileResources.INSTANCE.iconArrowRight());
            put(IconImages.ArrowUp.toString(), MobileResources.INSTANCE.iconArrowUp());
            put(IconImages.Back.toString(), MobileResources.INSTANCE.iconArrowLeft());
            put(IconImages.Check.toString(), MobileResources.INSTANCE.iconPadlock());
            put(IconImages.Delete.toString(), MobileResources.INSTANCE.iconDelete());
            put(IconImages.Forward.toString(), MobileResources.INSTANCE.iconFastforward());
            put(IconImages.Gear.toString(), MobileResources.INSTANCE.iconPreferences());
            put(IconImages.Grid.toString(), MobileResources.INSTANCE.iconDatabase());
            put(IconImages.Home.toString(), MobileResources.INSTANCE.iconHouse());
            put(IconImages.Info.toString(), MobileResources.INSTANCE.iconNewspaper());
            put(IconImages.Minus.toString(), MobileResources.INSTANCE.iconMinus());
            put(IconImages.Plus.toString(), MobileResources.INSTANCE.iconPlus());
            put(IconImages.Refresh.toString(), MobileResources.INSTANCE.iconRefresh());
            put(IconImages.Search.toString(), MobileResources.INSTANCE.iconMagnifyingGlass());
            put(IconImages.Star.toString(), MobileResources.INSTANCE.iconStar());
        }
    };	
	
}
