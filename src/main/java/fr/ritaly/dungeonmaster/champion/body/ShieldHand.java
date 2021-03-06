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
package fr.ritaly.dungeonmaster.champion.body;

import fr.ritaly.dungeonmaster.item.CarryLocation;

/**
 * The champion's hand that holds the shield.
 *
 * @author <a href="mailto:francois.ritaly@gmail.com">Francois RITALY</a>
 */
public class ShieldHand extends Hand {

	public ShieldHand(Body body) {
		super(body);
	}

	@Override
	public Type getType() {
		return Type.SHIELD_HAND;
	}

	@Override
	public final CarryLocation getCarryLocation() {
		return CarryLocation.HANDS;
	}

	@Override
	public boolean isWoundable() {
		return true;
	}
}
