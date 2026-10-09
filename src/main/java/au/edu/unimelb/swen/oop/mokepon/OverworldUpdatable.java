package au.edu.unimelb.swen.oop.mokepon;

// observer design pattern interface. Allows observable objects to have functions called for key game events in the
// overworld.
public interface OverworldUpdatable {
    void onCreation();
    void onMove();
    void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock);
    void onEventEnd(String eventMessage, TileEntity eventCaller);
    void onExit();
}
