package org.adoptopenjdk.lambda.tutorial.exercise2;

/*
 * #%L
 * lambda-tutorial
 * %%
 * Copyright (C) 2013 Adopt OpenJDK
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Some (inaccurate) London electrical districts
 * 
 * Lambda Tutorial -- Adopt Open JDK
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
public enum ElectoralDistrict {

	CROYDON("CR"), BARKING("BA"), HACKNEY("HA"), EDMONTON("ED");

	private final String prefix;

	ElectoralDistrict(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Complete this method to pass Exercise_2_Test#setOfVotersInDistrict()
	 * 
	 * @param district
	 *            - District to vote in
	 * @param voters
	 *            - voters to filter
	 * @return filtered set of registered voters in a district
	 */
	public static Set<RegisteredVoter> votersIn(ElectoralDistrict district, Collection<RegisteredVoter> voters) {
		//solution without the unmodifiableSet conversion for the setVotersInDistrict solution only
		//Set<RegisteredVoter> votersInDistrict = voters.stream()
		//		.filter(x -> x.getElectorId().substring(0, 2).equals(district.prefix)).collect(Collectors.toSet());

		Set<RegisteredVoter> votersInDistrict = voters.stream()
				.filter(x -> x.getElectorId().substring(0, 2).equals(district.prefix))
				.collect(Collectors.collectingAndThen(Collectors.toSet(),Collections::unmodifiableSet));

		return votersInDistrict;
	}


	/**
	 * Complete this method to pass Exercise_2_Test#removeAllSpoiledBallots()
	 * 
	 * @param ballots
	 *            - ballots to filter
	 * @return filtered set of unspoiled ballots
	 */
	public static Set<Ballot> unspoiledBallots(Set<Ballot> ballots) {
		Set<Ballot> unspoiledBallots = ballots.stream().filter(x->!x.isSpoiled()).collect(Collectors.toSet());
		return unspoiledBallots;
	}

	public String getPrefix() {
		return prefix;
	}
}
