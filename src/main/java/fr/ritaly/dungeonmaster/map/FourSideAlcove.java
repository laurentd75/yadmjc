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
package fr.ritaly.dungeonmaster.map;

import java.util.List;

import org.apache.commons.lang.Validate;

import fr.ritaly.dungeonmaster.Direction;
import fr.ritaly.dungeonmaster.Sector;
import fr.ritaly.dungeonmaster.ai.Creature;
import fr.ritaly.dungeonmaster.champion.Party;
import fr.ritaly.dungeonmaster.item.Item;

// FIXME FourSideAlcove doit impl�menter HasActuator
/**
 * @author <a href="mailto:francois.ritaly@gmail.com">Francois RITALY</a>
 */
public final class FourSideAlcove extends Element {

	public FourSideAlcove() {
		super(Element.Type.FOUR_SIDE_ALCOVE);
	}

	@Override
	public String getSymbol() {
		return "4";
	}

	@Override
	public boolean isTraversable(Party party) {
		return false;
	}

	@Override
	public boolean isTraversable(Creature creature) {
		Validate.notNull(creature, "The given creature is null");

		return creature.isImmaterial();
	}

	@Override
	public boolean isTraversableByProjectile() {
		return false;
	}

	public final List<Item> getItems(Direction direction) {
		// Appel de la m�thode non surcharg�e
		return super.getItems(map(direction));
	}

	public final Item pickItem(Direction direction) {
		// Appel de la m�thode non surcharg�e
		return super.removeItem(map(direction));
	}

	public final void dropItem(Item item, Direction direction) {
		// Appel de la m�thode non surcharg�e
		super.addItem(item, map(direction));
	}

	@Override
	public final List<Item> getItems(Sector sector) {
		// Surcharge pour forcer l'appel � la bonne m�thode
		throw new UnsupportedOperationException();
	}

	@Override
	public final Item removeItem(Sector corner) {
		// Surcharge pour forcer l'appel � la bonne m�thode
		throw new UnsupportedOperationException();
	}

	@Override
	public final void addItem(Item item, Sector corner) {
		// Surcharge pour forcer l'appel � la bonne m�thode
		throw new UnsupportedOperationException();
	}

	private Direction map(Sector sector) {
		Validate.notNull(sector, "The given sector is null");

		switch (sector) {
		case NORTH_EAST:
			return Direction.NORTH;
		case NORTH_WEST:
			return Direction.SOUTH;
		case SOUTH_EAST:
			return Direction.EAST;
		case SOUTH_WEST:
			return Direction.WEST;
		}

		throw new UnsupportedOperationException();
	}

	private Sector map(Direction direction) {
		Validate.notNull(direction, "The given direction is null");

		switch (direction) {
		case NORTH:
			return Sector.NORTH_EAST;
		case SOUTH:
			return Sector.NORTH_WEST;
		case EAST:
			return Sector.SOUTH_EAST;
		case WEST:
			return Sector.SOUTH_WEST;
		}

		throw new UnsupportedOperationException();
	}

	@Override
	public void validate() throws ValidationException {
		if (hasParty()) {
			throw new ValidationException(
					"An invisible wall can't have champions");
		}
	}

	@Override
	public boolean isFluxCageAllowed() {
		return false;
	}
}