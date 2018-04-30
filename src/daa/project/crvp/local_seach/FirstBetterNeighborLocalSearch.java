package daa.project.crvp.local_seach;

import daa.project.crvp.local_search.LocalSearch;
import daa.project.crvp.moves.Move;
import daa.project.crvp.problem.CVRPSolution;
import daa.project.crvp.utils.DoubleCompare;

/**
 * Local search algorithm that searches through all neighbors of a solution until one better
 * than the current, and continues until the current neighbor is the best of the neighborhood 
 * (local optimum)
 * 
 * 
 * @author Carlos Dominguez Garcia (alu0100966589)
 * @version 1.0.0
 * @since 1.0.0 (Apr 22, 2018)
 * @file FirstBetterNeighborLocalSearch.java
 *
 */
public class FirstBetterNeighborLocalSearch extends LocalSearch {
    
    public FirstBetterNeighborLocalSearch(Move moveToUse) {
        super(moveToUse);
    }
    
    @Override
    public CVRPSolution findLocalOptimum(CVRPSolution baseSolution) {
        if (baseSolution == null || !baseSolution.isFeasible()) {
            throw new IllegalAccessError("invalid initial solution, it is null or unfeasible");
        }
        boolean isLocalOptimum = true;
        Move move = getMove();
        CVRPSolution currentBestSolution = baseSolution;
        
        // While the solution is not locally an optimum, find the best neighbor
        // and try again with that neighbor
        do {
            isLocalOptimum = true;
            move.setSolution(currentBestSolution);
            while (move.hasMoreNeighbors() && isLocalOptimum) {
                move.nextNeighbor();
                if (move.isCurrentNeighborFeasible() && DoubleCompare.lessThan(move.getCurrentNeighborCost(), currentBestSolution.getTotalDistance())) {
                    isLocalOptimum = false;
                    currentBestSolution = move.getCurrentNeighbor();
                }
            }
        } while (!isLocalOptimum);
        
        return currentBestSolution;
    }
    
}
