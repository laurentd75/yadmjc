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

public class WaterSkinTest extends TestCase {

	public WaterSkinTest() {
	}

	public WaterSkinTest(String name) {
		super(name);
	}

	public void testWaterSkin() {
		Champion tiggy = ChampionFactory.getFactory().newChampion(Name.TIGGY);

		Party party = new Party();
		party.addChampion(tiggy);

		final WaterSkin waterSkin = new WaterSkin();

		// --- The water skin is initially empty
		assertEquals(0.3f * 0 + 0.3f, waterSkin.getWeight(), 0.0001f);

		// --- Fill the water skin
		waterSkin.fill();

		// --- Check the water skin is full
		assertEquals(0.3f * 3 + 0.3f, waterSkin.getWeight(), 0.0001f);

		// --- Tiggy drinks once, the water skin's weight decreased
		Item item1 = tiggy.consume(waterSkin);

		assertEquals(waterSkin, item1);
		assertEquals(0.3f * 2 + 0.3f, waterSkin.getWeight(), 0.0001f);

		// --- Tiggy drinks again, the water skin's weight decreased
		Item item2 = tiggy.consume(waterSkin);

		assertEquals(waterSkin, item2);
		assertEquals(0.3f * 1 + 0.3f, waterSkin.getWeight(), 0.0001f);

		// --- Tiggy drinks again, the water skin's weight decreased
		Item item3 = tiggy.consume(waterSkin);

		assertEquals(waterSkin, item3);
		assertEquals(0.3f * 0 + 0.3f, waterSkin.getWeight(), 0.0001f);

		// --- The water skin must be empty
		assertTrue(waterSkin.isEmpty());
	}

	@Override
	protected void setUp() throws Exception {
		Clock.getInstance().reset();
	}
}