package com.example.joshi.can;

import com.example.joshi.can.Exception.XMustBeLargerThanZeroException;
import com.example.joshi.can.Exception.YMustBeLargerThanZeroException;
import com.example.joshi.can.Logic.Corner;
import com.example.joshi.can.Logic.Node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test_Node_checkIfInMyZone_True()
    {
        Node node = new Node();
        try {
            Corner bottomLeftCorner = new Corner(3, 5);
            Corner bottomRightCorner= new Corner(6, 5);
            Corner topLeftCorner    = new Corner(3, 10);
            Corner topRightCorner   = new Corner(6, 10);
            node.setBottomLeftCorner(bottomLeftCorner);
            node.setBottomRightCorner(bottomRightCorner);
            node.setTopLeftCorner(topLeftCorner);
            node.setTopRightCorner(topRightCorner);
        }catch( XMustBeLargerThanZeroException e)
        {

        }catch (YMustBeLargerThanZeroException e)
        {

        }
        assertEquals(true, node.checkIfInMyZone(4,6));
        assertEquals(true, node.checkIfInMyZone(6,6));
        assertEquals(true, node.checkIfInMyZone(5,9));
        assertEquals(true, node.checkIfInMyZone(6,10));
    }
    @Test
    public void test_Node_checkIfInMyZone_False()
    {
        Node node = new Node();
        try {
            Corner bottomLeftCorner = new Corner(3, 5);
            Corner bottomRightCorner= new Corner(6, 5);
            Corner topLeftCorner    = new Corner(3, 10);
            Corner topRightCorner   = new Corner(6, 10);
            node.setBottomLeftCorner(bottomLeftCorner);
            node.setBottomRightCorner(bottomRightCorner);
            node.setTopLeftCorner(topLeftCorner);
            node.setTopRightCorner(topRightCorner);
        }catch( XMustBeLargerThanZeroException e)
        {

        }catch (YMustBeLargerThanZeroException e)
        {

        }
        assertEquals(false, node.checkIfInMyZone(3,10));
        assertEquals(false, node.checkIfInMyZone(6,5));
        assertEquals(false, node.checkIfInMyZone(3,5));
    }
}