/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.ritaly.dungeonmaster.item;

import junit.framework.TestCase;
import fr.ritaly.dungeonmaster.Clock;
import fr.ritaly.dungeonmaster.champion.Champion;
import fr.ritaly.dungeonmaster.champion.Champion.Name;
import fr.ritaly.dungeonmaster.champion.ChampionFactory;
import fr.ritaly.dungeonmaster.champion.Party;
import fr.ritaly.dungeonmaster.stat.Stat;

public class RabbitFootTest extends TestCase {

	public RabbitFootTest() {
	}

	public RabbitFootTest(String name) {
		super(name);
	}

	public void testRabbitsFoot() {
		// The rabbit's foot increases the champion's luck
		Champion tiggy = ChampionFactory.getFactory().newChampion(Name.TIGGY);

		Party party = new Party();
		party.addChampion(tiggy);

		final Stat luck = tiggy.getStats().getLuck();
		final int initialLuck = luck.value();

		// The rabbit's foot works no matter where it's worn
		final Item rabbitsFoot = new MiscItem(Item.Type.RABBIT_FOOT);

		// --- When in weapon hand, the luck increases by 10 points
		assertNull(tiggy.getBody().getWeaponHand().putOn(rabbitsFoot));
		assertEquals(initialLuck + 10, luck.value());

		assertEquals(rabbitsFoot, tiggy.getBody().getWeaponHand().takeOff());
		assertEquals(initialLuck, luck.value());

		// --- When in shield hand, the luck increases by 10 points
		assertNull(tiggy.getBody().getShieldHand().putOn(rabbitsFoot));
		assertEquals(initialLuck + 10, luck.value());

		assertEquals(rabbitsFoot, tiggy.getBody().getShieldHand().takeOff());
		assertEquals(initialLuck, luck.value());

		// --- The rabbit's foot can't be put on other body parts
		assertEquals(initialLuck, luck.value());
		assertEquals(rabbitsFoot, tiggy.getBody().getHead().putOn(rabbitsFoot));
		assertEquals(initialLuck, luck.value());
		assertEquals(rabbitsFoot, tiggy.getBody().getNeck().putOn(rabbitsFoot));
		assertEquals(initialLuck, luck.value());
		assertEquals(rabbitsFoot, tiggy.getBody().getTorso().putOn(rabbitsFoot));
		assertEquals(initialLuck, luck.value());
		assertEquals(rabbitsFoot, tiggy.getBody().getLegs().putOn(rabbitsFoot));
		assertEquals(initialLuck, luck.value());
		assertEquals(rabbitsFoot, tiggy.getBody().getFeet().putOn(rabbitsFoot));
		assertEquals(initialLuck, luck.value());

		// --- The rabbit's foot works even when stored in the champion's inventory

		// Check when inside the back pack
		assertTrue(tiggy.getInventory().getBackPack().add(rabbitsFoot) != -1);
		assertEquals(initialLuck + 10, luck.value());
		assertTrue(tiggy.getInventory().getBackPack().remove(rabbitsFoot));
		assertEquals(initialLuck, luck.value());

		// Check when inside the pouch
		assertTrue(tiggy.getInventory().getPouch().add(rabbitsFoot) != -1);
		assertEquals(initialLuck + 10, luck.value());
		assertTrue(tiggy.getInventory().getPouch().remove(rabbitsFoot));
		assertEquals(initialLuck, luck.value());

		// The rabbit's foot doesn't fit in the quiver / sheath
		assertEquals(-1, tiggy.getInventory().getQuiver().add(rabbitsFoot));
		assertEquals(initialLuck, luck.value());

		// FIXME Check the rabbit's foot when inside a chest inside a champion's inventory
	}

	@Override
	protected void setUp() throws Exception {
		Clock.getInstance().reset();
	}
}