## How to contribute to Paradise Lost

### If you found an issue:

* **If the issue is a security vulnerability, DO NOT OPEN AN ISSUE ON GITHUB**. Instead, to refer to our [security policy](https://github.com/devs-immortal/Paradise-Lost/blob/b1.7/SECURITY.md).

* **Check that the issue was not already reported** by searching on GitHub under [Issues](https://github.com/devs-immortal/paradise-lost/issues). If an issue already exists, feel free to leave a comment on it with any important information.

* If you're unable to find an open issue addressing the problem, [open a new one](https://github.com/devs-immortal/Paradise-Lost/issues/new/choose).

* Be sure to use the relevant bug report templates to create the issue:
  * [**Crash Report** for issues involving a crash](https://github.com/devs-immortal/Paradise-Lost/issues/new?assignees=&labels=Bug&template=crash-report.yml&title=Crash%3A+)
  * [**Bug Report** for other issues](https://github.com/devs-immortal/Paradise-Lost/issues/new?assignees=&labels=Bug&template=bug_report.yml&title=Bug%3A+)
  * [**Accessibility** for accessibility requests](https://github.com/devs-immortal/Paradise-Lost/issues/new?assignees=&labels=Feature%2CAccessibility&template=accessibility_report.yml&title=Accessibility%3A+)
  * [**Feature Request** for other suggestions](https://github.com/devs-immortal/Paradise-Lost/issues/new?assignees=&labels=Feature&template=feature_request.yml&title=Feature%3A+)

### For code contributions:

#### **Did you write a patch that fixes a bug?**

* Open a new GitHub pull request with the patch.

* Ensure the PR description clearly describes the problem and solution. Use the PR template. Include the relevant issue number if applicable.

* Before submitting, please check that your code matches our code style (Example):
```java
    /**
     * This code is actually gibberish. It's a mishmash of things intended to show our code style.
     * <br>~ Jack
     */
    @Inject(
        method = "tickEntities", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/client/world/ClientWorld;tickBlockEntities()V"
        ),
        cancellable = true
    )
    private void postEntityTick(CallbackInfo ci) {
        for (Entity entity : entityList) {
            if (entity instanceof PostTickEntity postTickEntity) {
                postTickEntity.postTick();
                ci.cancel();
            }
        }
        BlockLikeSet.getAllStructures().forEachRemaining(BlockLikeSet::postTick);
    }
    // Oh, and use spaces, not tabs.
```
#### **Do you have write access to the repo and want to make a change?**

* **MAKE A PULL REQUEST FOR NEW FEATURES**.
* Refer to CDA's details on contribution and branch names:
```
**<MCVER>**: Assume these are production/live branches unless otherwise written or stated.
  - Only push code here that is tested and expected to ship in the next applicable update
**experimental/<MCVER>**: Experimental Snapshot branches (Separate category due to mojang typically using different baseline versions)
  - Only port changes are allowed to be pushed here, as this branch is typically rebased off the latest stable/matching **<MCVER>** baseline.
**snapshots**: Primary snapshot branch, typically the next short-term minecraft update
  - Same regulations as **experimental/<MCVER>**, only port changes should be in this branch.

**feature/<TARGET>/<FEATURE_NAME>**: Feature branches designated for the specified upcoming release
  - These branches get merged into **next** when applicable, ready, and tested enough to be accepted into **next**

**next**: Branch designated for the next major revision to project, typically baselined off the latest game version (Or pre-release if applicable)
  - Segment into **next/<VERSION>** if multiple releases are in active development
  - In the case of the baseline being a snapshot/pre-releases, **Do not push port changes here -- they will eventually be baselined into next via rebase or merging the baseline into next**
```
#### **Did you fix whitespace, format code, or make a purely cosmetic patch?**

* Changes that are cosmetic in nature and do not add anything substantial to the project will likely not be accepted.

* Documentation changes may be accepted if the changes are not just grammar fixes.

#### **Do you intend to add a new feature or change an existing one?**

* Suggest your change as a feature request or to our [discord](https://discord.gg/eRsJ6F3Wng) first. If you don't, you might spend a bunch of time on something we end up rejecting.

#### **Do you have questions about the source code?**

* Ask any question about our code in our [discord](https://discord.gg/eRsJ6F3Wng). You can also use the blank issue form, but it is not recommended.

#### **Do you want to contribute to documentation?**

* Please see [this file](https://github.com/devs-immortal/Paradise-Lost/blob/2425948c10ba869fd0365568e7cfe5635ec66317/src/main/java/net/id/aether/Aether.java#L45-L62) and the following example about documentation:
```java
    /**
     * Write reasonable documentation for non-obvious methods.
     * For public facing APIs, like {@link net.id.aether.api.MoaAPI} or {@link net.id.aether.api.FloatingBlockHelper}, 
     * fully document everything that is public. ^ (Also, use these @link tags when possible)
     * <br>Use @param and @return tags if non-obvious or if specific values may give strange results.
     * <br>If you didn't write the code and you're just adding docs, end your docs with a tilde and your
     * name/username. If you did write it, then just the @author tag is alright.
     * <br>~ Jack
     */
```
Paradise Lost is nothing without its community. We encourage you to pitch in and help out or join the team!

Thanks for reading! :heart: :heart: :heart:

-Immortal Devs
