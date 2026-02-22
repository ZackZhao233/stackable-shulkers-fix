package dev.kikugie.shulkerfix.carpet;

import carpet.settings.Rule;

public class ShulkerFixSettings {
	private static final String SHULKERFIX = "shulkerfix";
	private static final String INTRUSIVE = "intrusive";
	private static final String BUGFIX = "bugfix";
	private static final String FEATURE = "feature";
	private static final String EXPERIMENTAL = "experimental";

	@Rule(
		desc = "Controls whenever hopper transfers should stack shulkers in containers.",
		category = {SHULKERFIX, INTRUSIVE, BUGFIX}
	)
	public static boolean hopperShulkerStacking = false;

	@Rule(
		desc = "Makes stacked shulkers create stronger comparator output, also known as OSS/HSS.",
		category = {SHULKERFIX, INTRUSIVE, BUGFIX}
	)
	public static boolean overstackedShulkerSignalStrength = true;

	@Rule(
		desc = "Fixes displayed shulker amount on vanilla clients. See issue #1899 in Carpet's repo.",
		category = {SHULKERFIX, BUGFIX, EXPERIMENTAL}
	)
	public static boolean clientShulkerSync = false;

	@Rule(
		desc = "Makes hoppers pick up single shulkers from a stack entity.",
		category = {SHULKERFIX, FEATURE, EXPERIMENTAL}
	)
	public static boolean hopperCollectSingleShulkers = false;

	@Rule(
		desc = "Makes hopper minecarts pick up single shulkers from a stack entity.",
		category = {SHULKERFIX, FEATURE, EXPERIMENTAL}
	)
	public static boolean minecartCollectSingleShulkers = false;

	@Rule(
		desc = "Disables additional slowdown caused by overstacked shulker boxes when set to false.",
		category = {SHULKERFIX, FEATURE, EXPERIMENTAL}
	)
	public static boolean overstackedMinecartSlowdown = true;
}
