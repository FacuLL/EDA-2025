package ar.edu.itba.eda;

import ar.edu.itba.eda.core.DijkstraPath;
import ar.edu.itba.eda.core.GraphFactory;
import ar.edu.itba.eda.core_service.GraphBuilder;
import ar.edu.itba.eda.core_service.GraphService;
import ar.edu.itba.eda.use.EmptyEdgeProp;
import ar.edu.itba.eda.use.WeightedEdge;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests {

    @Test
    public void testAddVerticesAndEdgesAndCounts() {
        GraphService<Integer, EmptyEdgeProp> g = new GraphBuilder<Integer, EmptyEdgeProp>()
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withStorage(GraphService.Storage.SPARSE)
                .build();

        g.addVertex(1);
        g.addVertex(2);
        g.addEdge(1, 2, new EmptyEdgeProp());

        assertEquals(2, g.numberOfVertices());
        assertEquals(1, g.numberOfEdges());
    }

    @Test
    public void testBFSDFSIterable() {
        GraphService<Integer, EmptyEdgeProp> g = new GraphBuilder<Integer, EmptyEdgeProp>()
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .withDirected(GraphService.EdgeMode.DIRECTED)
                .withAcceptSelfLoop(GraphService.SelfLoop.YES)
                .withAcceptWeight(GraphService.Weight.NO)
                .withStorage(GraphService.Storage.SPARSE)
                .build();

        g.addEdge(1, 2, new EmptyEdgeProp());
        g.addEdge(1, 3, new EmptyEdgeProp());
        g.addEdge(2, 4, new EmptyEdgeProp());

        Set<Integer> bfs = new HashSet<>();
        for (Integer v : g.getBFS(1)) bfs.add(v);
        assertEquals(4, bfs.size());
        assertTrue(bfs.contains(1));
        assertTrue(bfs.contains(2));
        assertTrue(bfs.contains(3));
        assertTrue(bfs.contains(4));

        Set<Integer> dfs = new HashSet<>();
        for (Integer v : g.getDFS(1)) dfs.add(v);
        assertEquals(4, dfs.size());
        assertTrue(dfs.contains(1));
        assertTrue(dfs.contains(2));
        assertTrue(dfs.contains(3));
        assertTrue(dfs.contains(4));
    }

    @Test
    public void testDijkstraPaths() {
        GraphService<Character, WeightedEdge> d = GraphFactory.create(
                GraphService.Multiplicity.SIMPLE,
                GraphService.EdgeMode.DIRECTED,
                GraphService.SelfLoop.NO,
                GraphService.Weight.YES,
                GraphService.Storage.SPARSE);

        d.addEdge('A', 'B', new WeightedEdge(10));
        d.addEdge('A', 'C', new WeightedEdge(3));
        d.addEdge('B', 'C', new WeightedEdge(1));
        d.addEdge('B', 'D', new WeightedEdge(2));
        d.addEdge('C', 'A', new WeightedEdge(1));
        d.addEdge('C', 'B', new WeightedEdge(4));
        d.addEdge('C', 'D', new WeightedEdge(8));
        d.addEdge('C', 'E', new WeightedEdge(2));
        d.addEdge('D', 'E', new WeightedEdge(7));
        d.addEdge('E', 'D', new WeightedEdge(9));
        d.addEdge('Z', 'K', new WeightedEdge(17));
        d.addEdge('K', 'A', new WeightedEdge(19));

        DijkstraPath<Character, WeightedEdge> pathRta = d.dijsktra('A');

        // According to implementation getShortestPathTo returns "[no path to X]" when prev is null
        assertEquals("[no path to A]", pathRta.getShortestPathTo('A'));
        assertEquals("[A, C, B]", pathRta.getShortestPathTo('B'));
        assertEquals("[A, C]", pathRta.getShortestPathTo('C'));
        assertEquals("[A, C, E]", pathRta.getShortestPathTo('E'));
        assertEquals("[no path to K]", pathRta.getShortestPathTo('K'));
        assertEquals("[no path to Z]", pathRta.getShortestPathTo('Z'));
    }

    @Test
    public void testIsBipartite() {
        GraphService<String, EmptyEdgeProp> g = new GraphBuilder<String, EmptyEdgeProp>()
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withStorage(GraphService.Storage.SPARSE)
                .build();

        g.addEdge("A", "B", new EmptyEdgeProp());
        g.addEdge("B", "C", new EmptyEdgeProp());
        g.addEdge("C", "A", new EmptyEdgeProp());

        assertFalse(g.isBipartite());

        GraphService<String, EmptyEdgeProp> g2 = new GraphBuilder<String, EmptyEdgeProp>()
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withStorage(GraphService.Storage.SPARSE)
                .build();

        g2.addEdge("A", "B", new EmptyEdgeProp());
        g2.addEdge("B", "C", new EmptyEdgeProp());

        assertTrue(g2.isBipartite());
    }

    @Test
    public void testHasCycle() {
        GraphService<Integer, EmptyEdgeProp> g = new GraphBuilder<Integer, EmptyEdgeProp>()
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withAcceptSelfLoop(GraphService.SelfLoop.YES)
                .withAcceptWeight(GraphService.Weight.NO)
                .withStorage(GraphService.Storage.SPARSE)
                .build();

        g.addEdge(1, 2, new EmptyEdgeProp());
        g.addEdge(2, 3, new EmptyEdgeProp());

        assertFalse(g.hasCycle());

        g.addEdge(3, 1, new EmptyEdgeProp());
        assertTrue(g.hasCycle());
    }

    @Test
    public void testPrintAllPaths() {
        GraphService<String, EmptyEdgeProp> g = new GraphBuilder<String, EmptyEdgeProp>()
                .withMultiplicity(GraphService.Multiplicity.SIMPLE)
                .withDirected(GraphService.EdgeMode.UNDIRECTED)
                .withAcceptSelfLoop(GraphService.SelfLoop.NO)
                .withAcceptWeight(GraphService.Weight.NO)
                .withStorage(GraphService.Storage.SPARSE)
                .build();

        g.addEdge("A", "B", new EmptyEdgeProp());
        g.addEdge("B", "C", new EmptyEdgeProp());
        g.addEdge("A", "C", new EmptyEdgeProp());

        // capture stdout
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        g.printAllPaths("A", "C");

        System.out.flush();
        System.setOut(originalOut);

        String out = baos.toString();
        // should contain at least two different paths
        assertTrue(out.contains("[A, C]") || out.contains("[A, B, C]"));
        assertTrue(out.contains("[A, C]") || out.contains("[A, B, C]"));
    }
}

