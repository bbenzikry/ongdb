/*
 * Copyright (c) 2018-2020 "Graph Foundation"
 * Graph Foundation, Inc. [https://graphfoundation.org]
 *
 * Copyright (c) 2002-2020 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of ONgDB.
 *
 * ONgDB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.transaction.state;

import org.neo4j.kernel.impl.store.record.NodeRecord;
import org.neo4j.kernel.impl.store.record.RelationshipRecord;
import org.neo4j.kernel.impl.util.DirectionWrapper;

public class DirectionIdentifier
{
    private DirectionIdentifier()
    {
    }

    public static DirectionWrapper wrapDirection( RelationshipRecord rel, NodeRecord startNode )
    {
        boolean isOut = rel.getFirstNode() == startNode.getId();
        boolean isIn = rel.getSecondNode() == startNode.getId();
        assert isOut | isIn;
        if ( isOut & isIn )
        {
            return DirectionWrapper.BOTH;
        }
        return isOut ? DirectionWrapper.OUTGOING : DirectionWrapper.INCOMING;
    }
}
