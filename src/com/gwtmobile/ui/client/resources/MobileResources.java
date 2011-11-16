package com.gwtmobile.ui.client.resources;



import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


public interface MobileResources extends ClientBundle {

	public static final MobileResources INSTANCE =  GWT.create(MobileResources.class);
	
	public enum StyleVariants { None, Var1, Var2, Var3, Var4, Var5};
	
	public enum IconImages { None, Custom, ArrowLeft, ArrowRight, ArrowUp, ArrowDown, Delete, Plus, Minus, Check, Gear, Refresh, Forward, Back, Grid, Star, Alert, Info, Home, Search };

	@Source("imageicon/clear.cache.gif") ImageResource iconClearCache();
	
	@Source("imageicon/alarm.png") ImageResource iconAlarm();
	@Source("imageicon/arrowdown.png") ImageResource iconArrowDown();
	@Source("imageicon/arrowdown2.png") ImageResource iconArrow2Down();
	@Source("imageicon/arrowleft.png") ImageResource iconArrowLeft();
	@Source("imageicon/arrowleft2.png") ImageResource iconArrow2Left();
	@Source("imageicon/arrowright.png") ImageResource iconArrowRight();
	@Source("imageicon/arrowright2.png") ImageResource iconArrow2Right();
	@Source("imageicon/arrowup.png") ImageResource iconArrowUp();
	@Source("imageicon/arrowup2.png") ImageResource iconArrow2Up();
	@Source("imageicon/bank.png") ImageResource iconBank();
	@Source("imageicon/briefcase.png") ImageResource iconBriefcase();
	@Source("imageicon/calculator.png") ImageResource iconCalculator();
	@Source("imageicon/camera.png") ImageResource iconCamera();
	@Source("imageicon/clapboard.png") ImageResource iconClapboard();
	@Source("imageicon/clock.png") ImageResource iconClock();
	@Source("imageicon/connections.png") ImageResource iconConnections();
	@Source("imageicon/credit_card.png") ImageResource iconCreditCard();
	@Source("imageicon/database.png") ImageResource iconDatabase();
	@Source("imageicon/delete.png") ImageResource iconDelete();
	@Source("imageicon/delivery.png") ImageResource iconDelivery();
	@Source("imageicon/disk.png") ImageResource iconDisk();
	@Source("imageicon/eject.png") ImageResource iconEject();
	@Source("imageicon/fastforward.png") ImageResource iconFastforward();
	@Source("imageicon/filter.png") ImageResource iconFilter();
	@Source("imageicon/fire.png") ImageResource iconFire();
	@Source("imageicon/first_aid.png") ImageResource iconFirstAid();
	@Source("imageicon/folder.png") ImageResource iconFolder();
	@Source("imageicon/forwardtoend.png") ImageResource iconForwardToEnd();
	@Source("imageicon/games.png") ImageResource iconGames();
	@Source("imageicon/graph.png") ImageResource iconGraph();
	@Source("imageicon/heart.png") ImageResource iconHeart();
	@Source("imageicon/house.png") ImageResource iconHouse();
	@Source("imageicon/lightbulb.png") ImageResource iconLightBulb();
	@Source("imageicon/magnet.png") ImageResource iconMagnet();
	@Source("imageicon/magnifyingglass.png") ImageResource iconMagnifyingGlass();
	@Source("imageicon/mailclosed.png") ImageResource iconMailClosed();
	@Source("imageicon/mailopened.png") ImageResource iconMailOpened();
	@Source("imageicon/man.png") ImageResource iconMan();
	@Source("imageicon/minus.png") ImageResource iconMinus();
	@Source("imageicon/monitor.png") ImageResource iconMonitor();
	@Source("imageicon/move.png") ImageResource iconMove();
	@Source("imageicon/newspaper.png") ImageResource iconNewspaper();
	@Source("imageicon/padlock.png") ImageResource iconPadlock();
	@Source("imageicon/page.png") ImageResource iconPage();
	@Source("imageicon/pause.png") ImageResource iconPause();
	@Source("imageicon/pencilangled.png") ImageResource iconPencilAngled();
	@Source("imageicon/pencilstraight.png") ImageResource iconPencilStraight();
	@Source("imageicon/photos.png") ImageResource iconPhotos();
	@Source("imageicon/piggy.png") ImageResource iconPiggy();
	@Source("imageicon/play.png") ImageResource iconPlay();
	@Source("imageicon/plus.png") ImageResource iconPlus();
	@Source("imageicon/preferences.png") ImageResource iconPreferences();
	@Source("imageicon/record.png") ImageResource iconRecord();
	@Source("imageicon/refresh.png") ImageResource iconRefresh();
	@Source("imageicon/reload.png") ImageResource iconReload();
	@Source("imageicon/rewind.png") ImageResource iconRewind();
	@Source("imageicon/rewindtostart.png") ImageResource iconRewindToStart();
	@Source("imageicon/rss.png") ImageResource iconRss();
	@Source("imageicon/safe.png") ImageResource iconSafe();
	@Source("imageicon/scales.png") ImageResource iconScales();
	@Source("imageicon/shoppingcart.png") ImageResource iconShoppingCart();
	@Source("imageicon/speaker.png") ImageResource iconSpeaker();
	@Source("imageicon/speechbubble.png") ImageResource iconSpeechBubble();
	@Source("imageicon/speechmedia.png") ImageResource iconSpeechMedia();
	@Source("imageicon/star.png") ImageResource iconStar();
	@Source("imageicon/trash.png") ImageResource iconTrash();
	@Source("imageicon/trend.png") ImageResource iconTrend();
	@Source("imageicon/world.png") ImageResource iconWorld();
	
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
