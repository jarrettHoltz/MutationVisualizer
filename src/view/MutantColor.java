package view;

import java.awt.Color;
import java.util.EnumMap;
import java.util.Map;

import model.MutantStatus;

public abstract class MutantColor {
	public static Map<ColorContext,Map<MutantStatus, Color>> colors = initColors();
	
	private static Map<ColorContext,Map<MutantStatus, Color>> initColors() {
		Map<ColorContext,Map<MutantStatus, Color>> colors = new EnumMap<ColorContext,Map<MutantStatus, Color>>(ColorContext.class);
		
		Map<MutantStatus, Color> highlightedColors = new EnumMap<MutantStatus, Color>(MutantStatus.class);
		highlightedColors.put(MutantStatus.LIVE, new Color(0xFF, 0xBB, 0xBB));
		for(MutantStatus killed : MutantStatus.getKilled()){
			highlightedColors.put(killed, new Color(0xBB, 0xFF, 0xBB));
		}
		colors.put(ColorContext.HIGHLIGHT, highlightedColors);
		
		Map<MutantStatus, Color> selectedColors = new EnumMap<MutantStatus, Color>(MutantStatus.class);
		selectedColors.put(MutantStatus.COVERED, new Color(0xDD, 0x99, 0x99));
		for(MutantStatus killed : MutantStatus.getKilled()){
			selectedColors.put(killed, new Color(0x99, 0xDD, 0x99));
		}
		selectedColors.put(MutantStatus.LIVE, new Color(0xAA, 0xAA, 0xAA));
		colors.put(ColorContext.SELECTED, selectedColors);
		colors.put(ColorContext.SOLID, selectedColors);
		return colors;
	}
	
	public static Color getColor(ColorContext context, MutantStatus status) {
		return colors.get(context).get(status);
	}
}
