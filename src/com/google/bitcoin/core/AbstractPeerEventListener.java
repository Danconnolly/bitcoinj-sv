/**
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.bitcoin.core;

/**
 * Convenience abstract class for implmenting a PeerEventListener.
 *
 * <p>The default method implementations do nothing.
 *
 * @author miron@google.com (Miron Cuperman)
 */
public class AbstractPeerEventListener extends Object implements PeerEventListener {
    public void onBlocksDownloaded(Peer peer, Block block, int blocksLeft) {
    }

    public void onChainDownloadStarted(Peer peer, int blocksLeft) {
    }

    public void onPeerConnected(Peer peer, int peerCount) {
    }

    public void onPeerDisconnected(Peer peer, int peerCount) {
    }

    public Message onPreMessageReceived(Peer peer, Message m) {
        // Just pass the message right through for further processing.
        return m;
    }
}
